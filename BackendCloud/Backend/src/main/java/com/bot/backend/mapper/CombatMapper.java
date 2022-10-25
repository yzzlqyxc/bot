package com.bot.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bot.backend.pojo.Combat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CombatMapper extends BaseMapper<Combat> {
}
