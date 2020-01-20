package com.whli.autumn.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whli.autumn.core.util.DateUtils;
import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.service.impl.BaseServiceImpl;
import com.whli.autumn.model.system.Barcode;
import com.whli.autumn.model.system.SequenceDetail;
import com.whli.autumn.system.constant.SequenceConstant;
import com.whli.autumn.system.dao.IBarcodeDao;
import com.whli.autumn.system.dto.BarcodeDTO;
import com.whli.autumn.system.service.IBarcodeService;
import com.whli.autumn.system.service.ISequenceDetailService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

;

/**
 * <p>条码生成业务实现</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Service("barcodeService")
public class BarcodeServiceImpl extends BaseServiceImpl<Barcode> implements IBarcodeService {

	@Autowired
	private IBarcodeDao barcodeDao;
	@Autowired
	private ISequenceDetailService sequenceDetailService;

	@Override
	public IBaseDao<Barcode> getDao() {
		return barcodeDao;
	}

	@Override
	public synchronized String generatorCode(String code) {
		List<SequenceDetail> detailList = sequenceDetailService.getByBarcode(code);
		if (CollectionUtils.isNotEmpty(detailList)){
			return handleSequenceDetail(detailList);
		}
		return null;
	}

	@Override
	public IPage<BarcodeDTO> listBarcodeByPage(BarcodeDTO entity, Integer pageNumber, Integer pageSize) {
		Page<BarcodeDTO> page = new Page<>(pageNumber,pageSize);
		List<BarcodeDTO> records = barcodeDao.listBarcodeByPage(page,entity);
		page.setRecords(records);
		return page;
	}

	/**
	 * 处理序列规则详细
	 * @param detailList
	 * @return
	 */
	private String handleSequenceDetail(List<SequenceDetail> detailList){
		String today = DateUtils.dateToString(new Date(),DateUtils.DEFAULT);
		StringBuffer barcode = new StringBuffer();
		for (SequenceDetail detail : detailList){
			String type = detail.getType();

			switch (type){
				case SequenceConstant.TEXT:
					barcode.append(detail.getValue());
					break;
				case SequenceConstant.NUMBER:
					Date update = detail.getUpdateDate() == null ? new Date() : detail.getUpdateDate();
					String updateStr = DateUtils.dateToString(update,DateUtils.DEFAULT);

					//判断最后的更新日期与今天是否相符，如不相等设定值为1,否则值+1
					if (StringUtils.isBlank(detail.getValue()) || !today.equals(updateStr)){
						detail.setValue("1");
					}else{
						int value = Integer.parseInt(detail.getValue());
						++value;
						detail.setValue(value+"");
					}

					//判断数字值是否满足数字位数，不满足则在前位补0
					StringBuffer number = new StringBuffer(detail.getValue());
					if (number.length() < detail.getLength()){
						int detailLength = detail.getLength() - number.length();
						for (int i=0; i < detailLength; i++){
							number.insert(0,"0");
						}
					}
					barcode.append(number.toString());
					//更新数值
					sequenceDetailService.updateByPK(detail);
					break;
				case SequenceConstant.DATE:
					String date = DateUtils.dateToString(new Date(),detail.getFormat());
					barcode.append(date);
					break;
			}

			//连接符
			if (StringUtils.isNotBlank(detail.getDelimit()))
				barcode.append(detail.getDelimit());
		}
		return barcode.toString();
	}

	@Override
	public Barcode validate(Barcode entity) {
		return barcodeDao.validate(entity);
	}
}
