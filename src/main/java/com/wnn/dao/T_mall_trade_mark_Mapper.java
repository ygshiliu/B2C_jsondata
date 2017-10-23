package com.wnn.dao;

import java.util.List;

import com.wnn.bean.T_mall_trade_mark;


public interface T_mall_trade_mark_Mapper {
	List<T_mall_trade_mark> select_spu_tm(Integer id);
}
