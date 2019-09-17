package com.phonecard.service;

import com.phonecard.bean.Company;
import com.phonecard.bean.JsonResult;
import com.phonecard.dao.CompanyMapper;
import com.phonecard.util.*;
import com.phonecard.vo.CompanyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/4 0004 16:33
 * @Description:
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    public JsonResult findCompanyAll(PageObject pageObject) {
        Map<String,Object> map = new HashMap<>(2);
        List<CompanyVo> list = companyMapper.findCompanyAll(pageObject);
        int row = companyMapper.getCompanyAllRow();
        pageObject.setRowCount(row);
        map.put("list",list);
        map.put("pageObject", pageObject);
        return new JsonResult(StatusCode.SUCCESS,"OK",map);
    }

    public JsonResult updateCompanyInfo(Company company) {
        int row = companyMapper.updateByPrimaryKeySelective(company);
        if (row <= 0){
            return new JsonResult(StatusCode.FAIL,"FAIL");
        }
        return new JsonResult(StatusCode.SUCCESS,"OK");
    }

    public JsonResult deleteCompany(Integer id) {
        int row = companyMapper.deleteCompany(id);
        if (row <= 0){
            return new JsonResult(StatusCode.FAIL,"FAIL");
        }
        return new JsonResult(StatusCode.SUCCESS,"OK");
    }

    public JsonResult saveCompanyInfo(Company company) {
        company.setCompanyCreateTime(new Date());
        company.setCompanyPassword(MD5Password.MD5("111111"));
        int row = companyMapper.insertSelective(company);
        if (row <= 0){
            return new JsonResult(StatusCode.FAIL,"FAIL");
        }
        return new JsonResult(StatusCode.SUCCESS,"OK");
    }

    public JsonResult findCompanyAllList() {
        List<Company> list = companyMapper.findCompanyAllList();
        return new JsonResult(StatusCode.SUCCESS,"OK",list);
    }
}
