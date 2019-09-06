package com.phonecard.service;

import com.phonecard.bean.AboutUs;
import com.phonecard.dao.AboutUsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutUsService {

    @Autowired
    private AboutUsMapper aboutUsMapper;

    public AboutUs getInfo() {
        return aboutUsMapper.selectByPrimaryKey(1);
    }

    public boolean updateInfo(AboutUs aboutUs) {
        int row =  aboutUsMapper.updateByPrimaryKeySelective(aboutUs);
        if(row > 0){
            return true;
        } else{
            return false;
        }
    }
}
