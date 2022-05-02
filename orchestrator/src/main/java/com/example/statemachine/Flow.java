package com.example.statemachine;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Flow {

    private State from;

    private State to;
}
