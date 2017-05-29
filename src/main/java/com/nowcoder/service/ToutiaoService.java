package com.nowcoder.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nowcoder on 2016/6/26.
 */
@Service
public class ToutiaoService {
    public String say() {
        List<Integer> list = new ArrayList<Integer>();
        return "This is from ToutiaoService";
    }
}
