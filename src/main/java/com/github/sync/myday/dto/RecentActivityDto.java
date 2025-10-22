package com.github.sync.myday.dto;

import com.github.sync.myday.enums.ActivityEnum;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecentActivityDto {

    private ActivityEnum activity;
    private String activityInfo;


    public RecentActivityDto() {
    }

    public RecentActivityDto(ActivityEnum activity) {
        this.activity = activity;
    }

    public RecentActivityDto(ActivityEnum activity, String activityInfo) {
        this.activity = activity;
        this.activityInfo = activityInfo;
    }
}
