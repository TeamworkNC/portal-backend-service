package com.moviesandchill.portalbackendservice.dto.film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Filter {

    private List<Integer> idages = new ArrayList<>();

    private List<Integer> idgenres = new ArrayList<>();

    private List<Integer> idproducers = new ArrayList<>();
}
