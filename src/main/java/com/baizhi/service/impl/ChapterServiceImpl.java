package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Resource
    private ChapterDao chapterDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> selectBypage(Integer rows, Integer page,String album_id) {
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
        List<Chapter> chapters = chapterDao.selectByPage(start, rows,album_id);

        //数据条数
        Integer count = chapterDao.selectCunt();

        //计算总页数
        Integer totalpage = count%rows == 0 ? count/rows:count/rows+1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",chapters);
        map.put("total",totalpage);
        map.put("records",count);
        return map;
    }

    @Override
    public void delete(String[] id) {
        chapterDao.delete(id);
    }

    @Override
    public Chapter selectById(String id) {
        Chapter chapter = chapterDao.selectById(id);
        return chapter;
    }
    @Override
    public void insert(Chapter chapter) {
        chapterDao.insert(chapter);
    }

    @Override
    public void update(Chapter chapter) {
        chapterDao.update(chapter);
    }

    //通过专辑id查
    @Override
    public List<Chapter> selectByalbumId(String id){
        List<Chapter> chapters = chapterDao.selectByalbumId(id);
        return chapters;
    }
}
