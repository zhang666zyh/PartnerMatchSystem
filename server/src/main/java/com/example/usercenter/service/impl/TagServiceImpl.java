package com.example.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.usercenter.mapper.TagMapper;
import com.example.usercenter.model.domain.Tag;
import com.example.usercenter.service.TagService;
import org.springframework.stereotype.Service;

/**
* @author 张宇航
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2023-09-29 18:22:14
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

}
