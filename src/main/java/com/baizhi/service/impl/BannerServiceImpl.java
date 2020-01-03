package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Month12Dto;
import com.baizhi.service.BannerService;
import io.goeasy.GoEasy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Resource
    private BannerDao bannerDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> selectBypage(Integer rows, Integer page) {
        /*
         * jqGrid要求必须返回：
         * page：当前页
         * rows:数据
         * total:总页数
         * records：总条数
         *
         * */

        /*
         * 计算起始
         *   rows:每页展示条数
         *   page：当前页
         *
         * */

        //开始条数
        Integer start = (page - 1) * rows;

        //返回的数据
        List<Banner> Banners = bannerDao.selectByPage(start,rows);

        //数据条数
        Integer count = bannerDao.selectCunt();

        //计算总页数
        Integer totalpage = count%rows == 0 ? count/rows:count/rows+1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",Banners);
        map.put("total",totalpage);
        map.put("records",count);
        return map;
    }

    @Override
    public void insert(Banner banner) {
        bannerDao.insert(banner);
    }

    @Override
    public void update(Banner banner) {
        bannerDao.update(banner);
    }

    @Override
    public void delete(String[] delete) {
        bannerDao.delete(delete);
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BC-caac2a998f3044c1a91cb1ce88f67093");
        goEasy.publish("zypDay7",bannerDao.selectDay7().toString());
        goEasy.publish("zypMonth12",selectMonth12().toString());
    }

    @Override
    public List<Banner> select() {
        List<Banner> select = bannerDao.select();
        return select;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int[] selectDay7() {
        int[] ints = bannerDao.selectDay7();
        return ints;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int[] selectMonth12() {
        Month12Dto month12Dto = bannerDao.selectMonth12();
        int[] a = new int[12];
        a[0]=month12Dto.getMonth1();
        a[1]=month12Dto.getMonth2();
        a[2]=month12Dto.getMonth3();
        a[3]=month12Dto.getMonth4();
        a[4]=month12Dto.getMonth5();
        a[5]=month12Dto.getMonth6();
        a[6]=month12Dto.getMonth7();
        a[7]=month12Dto.getMonth8();
        a[8]=month12Dto.getMonth9();
        a[9]=month12Dto.getMonth10();
        a[10]=month12Dto.getMonth11();
        a[11]=month12Dto.getMonth12();
        return a;
    }
}
