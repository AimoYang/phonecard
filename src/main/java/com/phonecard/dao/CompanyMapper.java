package com.phonecard.dao;

import com.phonecard.bean.Company;
import com.phonecard.util.PageObject;
import com.phonecard.vo.CompanyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    List<CompanyVo> findCompanyAll(@Param("pageObject")PageObject pageObject);

    int getCompanyAllRow();

    int deleteCompany(@Param("id")Integer id);
}