package com.github.sync.myday.mapper;

import com.github.sync.myday.dto.LikesDto;
import com.github.sync.myday.entity.LikeEntity;
import com.github.sync.myday.mapper.imp.MapperImp;
import com.github.sync.myday.mapper.imp.MapperListImp;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class LikesMapper implements MapperImp<LikeEntity, LikesDto>, MapperListImp<LikeEntity, LikesDto> {
    private final PostMapper mapper;
    private final UserMapper mapperUser;
    public LikesMapper(PostMapper mapper, UserMapper userMapper) {
        this.mapper = mapper;
        this.mapperUser = userMapper;
    }

    @Override
    public LikesDto convertToDto(LikeEntity object) {
        return null;
    }

    @Override
    public LikeEntity convertToEntity(LikesDto object) {
        return new LikeEntity(
            object.getId(),
                mapper.convertToEntity(object.getPost()),
                mapperUser.convertToEntity(object.getUser()),
                object.getDate(),
                object.getTime()
        );
    }

    @Override
    public List<LikeEntity> convertListToEntity(List<LikesDto> dtoList) {
        List<LikeEntity> memoryList = new ArrayList<>();
        for (LikesDto dto : dtoList) {
            memoryList.add(this.convertToEntity(dto));
        }
        return memoryList;


    }

    @Override
    public List<LikesDto> convertEntityToDTO(List<LikeEntity> dtoList) {
        return List.of();
    }
}
