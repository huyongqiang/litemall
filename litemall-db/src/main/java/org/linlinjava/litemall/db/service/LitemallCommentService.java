package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallCommentMapper;
import org.linlinjava.litemall.db.domain.LitemallComment;
import org.linlinjava.litemall.db.domain.LitemallCommentExample;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LitemallCommentService {
    @Resource
    private LitemallCommentMapper commentMapper;

    public List<LitemallComment> queryGoodsByGid(Integer id, int offset, int limit) {
        LitemallCommentExample example = new LitemallCommentExample();
        example.or().andValueIdEqualTo(id).andTypeIdEqualTo((byte)0);
        PageHelper.startPage(offset, limit);
        return commentMapper.selectByExample(example);
    }

    public int countGoodsByGid(Integer id, int offset, int limit) {
        LitemallCommentExample example = new LitemallCommentExample();
        example.or().andValueIdEqualTo(id).andTypeIdEqualTo((byte)0);
        return (int)commentMapper.countByExample(example);
    }

    public List<LitemallComment> query(Byte typeId, Integer valueId, Integer showType, Integer offset, Integer limit) {
        LitemallCommentExample example = new LitemallCommentExample();
        if(showType == 0) {
            example.or().andValueIdEqualTo(valueId).andTypeIdEqualTo(typeId);
        }
        else if(showType == 1){
            example.or().andValueIdEqualTo(valueId).andTypeIdEqualTo(typeId).andHasPictureEqualTo(true);
        }
        else{
            Assert.state(false, "showType不支持");
        }
        PageHelper.startPage(offset, limit);
        return commentMapper.selectByExample(example);
    }

    public int count(Byte typeId, Integer valueId, Integer showType, Integer offset, Integer size){
        LitemallCommentExample example = new LitemallCommentExample();
        if(showType == 0) {
            example.or().andValueIdEqualTo(valueId).andTypeIdEqualTo(typeId);
        }
        else if(showType == 1){
            example.or().andValueIdEqualTo(valueId).andTypeIdEqualTo(typeId).andHasPictureEqualTo(true);
        }
        else{
            Assert.state(false, "");
        }
        return (int)commentMapper.countByExample(example);
    }

    public Integer save(LitemallComment comment) {
        return commentMapper.insertSelective(comment);
    }


    public void update(LitemallComment comment) {
        commentMapper.updateByPrimaryKeySelective(comment);
    }


    public List<LitemallComment> querySelective(String userId, String valueId, Integer page, Integer size, String sort, String order) {
        LitemallCommentExample example = new LitemallCommentExample();
        LitemallCommentExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(userId)){
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if(!StringUtils.isEmpty(valueId)){
            criteria.andValueIdEqualTo(Integer.valueOf(valueId)).andTypeIdEqualTo((byte)0);
        }
        PageHelper.startPage(page, size);
        return commentMapper.selectByExample(example);
    }

    public int countSelective(String userId, String valueId, Integer page, Integer size, String sort, String order) {
        LitemallCommentExample example = new LitemallCommentExample();
        LitemallCommentExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(userId)){
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if(!StringUtils.isEmpty(valueId)){
            criteria.andValueIdEqualTo(Integer.valueOf(valueId)).andTypeIdEqualTo((byte)0);
        }
        return (int)commentMapper.countByExample(example);
    }

    public void updateById(LitemallComment collect) {
        commentMapper.updateByPrimaryKeySelective(collect);
    }

    public void deleteById(Integer id) {
        commentMapper.deleteByPrimaryKey(id);
    }

    public void add(LitemallComment category) {
        commentMapper.insertSelective(category);
    }

    public LitemallComment findById(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }
}
