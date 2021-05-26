package com.brt.oa.channel.service.impl;

import com.brt.oa.channel.dao.ChannelDao;
import com.brt.oa.channel.pojo.Channel;
import com.brt.oa.channel.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelDao channelDao;

    @Override
    public void insert(Channel record) {
      channelDao.insert(record);
    }

    @Override
    public void updateTagById(Integer id, Integer tag) {
        channelDao.updateTagById(id, tag);
    }

    @Override
    public List<Channel> selectChannel(String name) {
//        Integer start = (pageIndex-1)*pageSize;
//        Integer size = pageSize;
        return channelDao.selectChannel(name);
    }

    @Override
    public void updateById(Channel channel,Integer id) {
        channelDao.updateById(channel,id);
    }

    @Override
    public String findNameById(Integer channelid) {
        return channelDao.findNameById(channelid);
    }
}
