package com.w3studio.sflink.common;

/** 

 * Copyright (c) 2003-2011 Wonders Information Co.,Ltd. All Rights Reserved.
 * 5-6/F, 20 Bldg, 481 Guiping RD. Shanghai 200233,PRC
 *
 * This software is the confidential and proprietary information of Wonders Group.
 * (Research & Development Center). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gun.org
 */

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * Title: schoolpaper_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author dy
 * @version $Revision$ 2014-6-5
 * @author (lastest modification by $Author$)
 * @since 20130601
 */
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:dispatcher.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
// defaultRollback = true 则测试产生的数据，不会存入到数据库中
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public abstract class AbstractTestCase {
	/**
	 * 日志，可用于子类
	 */
	protected Logger log = Logger.getLogger(AbstractTestCase.class);

}
