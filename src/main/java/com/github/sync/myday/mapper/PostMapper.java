package com.github.sync.myday.mapper;

import com.github.sync.myday.dto.PostDto;
import com.github.sync.myday.entity.PostEntity;
import com.github.sync.myday.mapper.imp.MapperImp;
import org.springframework.stereotype.Component;


@Component
public class PostMapper implements MapperImp<PostEntity, PostDto> {
    private final UserMapper userMapper;

    public PostMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public PostDto convertToDto(PostEntity object) {
        return null;
    }

    @Override
    public PostEntity convertToEntity(PostDto object) {
        return new PostEntity(
                object.getId(),
                object.isGuest(),
                object.isComments(),
                object.getBody(),
                object.getEmotion(),
                null,
//                object.getListLike(),
                userMapper.convertToEntity(object.getUser())
        );
    }


}
