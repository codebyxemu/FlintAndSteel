package com.github.codebyxemu.flintandsteel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Tier {
	private int tier;
	private String display;
	private int chance;
	private int durabilityReduce;
}
