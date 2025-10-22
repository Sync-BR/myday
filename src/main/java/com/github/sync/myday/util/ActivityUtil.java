package com.github.sync.myday.util;

import com.github.sync.myday.dto.RecentActivityDto;
import com.github.sync.myday.enums.ActivityEnum;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@Getter
public class ActivityUtil {
    private List<RecentActivityDto> recentActivity;

    public ActivityUtil() {
        this.recentActivity = new ArrayList<>();
        this.recentActivity.add(new RecentActivityDto(ActivityEnum.REGISTERED, "Eduardo"));
    }

    public void addActivity(ActivityEnum activity, String activityName) {
        if (activity.equals(ActivityEnum.REGISTERED)) {
            this.recentActivity.add(new RecentActivityDto(activity, activityName));
        } else {
            this.recentActivity.add(new RecentActivityDto(activity));
        }
    }



}
