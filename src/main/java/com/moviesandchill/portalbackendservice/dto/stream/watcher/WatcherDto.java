package com.moviesandchill.portalbackendservice.dto.stream.watcher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WatcherDto {

    private Long watcherID;

    private Long userID;

}
