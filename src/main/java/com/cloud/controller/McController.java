package com.cloud.controller;

import com.cloud.entity.MLove;
import com.cloud.service.MLoveService;
import com.cloud.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/mc")
@CrossOrigin
public class McController {

    //注入
    @Autowired
    private MLoveService mLoveService;
    ///

    /**
     *
     * @param uid 根据此id查询三表联查信息
     * @return
     */
    @RequestMapping(value = "getAllMLove", method = RequestMethod.GET)
    @ResponseBody
    public List getAllMLove(int uid){
        List<MLove> list = mLoveService.getAllMLove(uid);

        Msg<MLove> msg = new Msg<>();

        msg.setData(list);

        return msg.getData();
    }

    /**
     * 删除喜欢的歌曲
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public int delete(int uid, int mid){
        int del = mLoveService.delete(uid, mid);

        Msg<MLove> msg = new Msg<>();

        msg.setCode(del);

        return msg.getCode();
    }

    /**
     * 添加前查询喜欢的歌曲是否存在
     */
    @RequestMapping(value = "selectOne", method = RequestMethod.GET)
    @ResponseBody
    public boolean selectOne(int uid, int mid){
        boolean b = mLoveService.selectOne(uid, mid);

        System.out.println(b);

        return b;
    }

    /**
     * 添加歌曲
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    @ResponseBody
    public int add(int uid, int mid){
        Msg<MLove> msg = new Msg<>();

        boolean b = mLoveService.selectOne(uid, mid);

        if (b==true){
            msg.setCode(0);
        }else {
            int add = mLoveService.add(uid, mid);

            msg.setCode(add);
        }
        return msg.getCode();
    }

}
