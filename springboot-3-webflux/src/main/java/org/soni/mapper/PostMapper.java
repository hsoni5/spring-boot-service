package org.soni.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.soni.domain.Post;
import org.soni.dto.PostDto;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface PostMapper {
    PostMapper MAPPER = Mappers.getMapper(PostMapper.class);

    @Mappings({
            @Mapping(source = "createdOn", target = "createdDate"),
            @Mapping(source = "updatedOn", target = "updatedDate")
    })
    PostDto mapToPostDto(Post post);

    @Mappings({
            @Mapping(source = "createdDate", target = "createdOn"),
            @Mapping(source = "updatedDate", target = "updatedOn")
    })
    Post mapToPost(PostDto postDto);

    List<PostDto> mapToPostDtoList(List<Post> postList);

    List<Post> mapToPosts(List<PostDto> postDtoList);
}
