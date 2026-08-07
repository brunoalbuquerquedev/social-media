package project.social.common.dtos.domain;

import project.social.domain.Post;

public record TrendingDto(
        Object item,
        String type,
        Integer score
) {
    public TrendingDto(Post post, Integer score) {
        this(post, "post", score);
    }

    public TrendingDto(Object item, String type, Integer score) {
        this.item = item;
        this.type = type;
        this.score = score;
    }
}
