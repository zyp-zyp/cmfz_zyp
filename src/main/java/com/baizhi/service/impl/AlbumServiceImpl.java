package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.service.AlbumService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Resource
    private AlbumDao albumDao;

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
        List<Album> albums = albumDao.selectByPage(start, rows);

        //数据条数
        Integer count = albumDao.selectCunt();

        //计算总页数
        Integer totalpage = count%rows == 0 ? count/rows:count/rows+1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",albums);
        map.put("total",totalpage);
        map.put("records",count);
        return map;
    }

    @Override
    public void insert(Album album) {
        albumDao.insert(album);
    }

    @Override
    public void update(Album album) {
        albumDao.update(album);
    }

    @Override
    public void delete(String[] id) {
        albumDao.delete(id);
    }

    //查询所有

    @Override
    public List<Album> select() {
        List<Album> select = albumDao.select();
        return select;
    }

    //id查

    @Override
    public Album selectById(String id) {
        Album album = albumDao.selectById(id);
        return album;
    }
}
