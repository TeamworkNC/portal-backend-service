package com.moviesandchill.portalbackendservice.dto.stream.watcher;

import com.moviesandchill.portalbackendservice.dto.stream.session.SessionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WatcherDto {

    private Long watcherID;

    private Long userID;

}
