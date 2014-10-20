package com.pm.rap.toe.util;

public class IntObject {
	private int value;
	
	public IntObject(){
		this(0);
	}

	public IntObject(int value) {
		super();
		this.value = value;
	}

	public int get() {
		return value;
	}

	public void set(int value) {
		this.value = value;
	}

	public int inc() {
		return inc(1);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntObject other = (IntObject) obj;
		if (value != other.value)
			return false;
		return true;
	}

	public int dec() {
		return inc(-1);
	}

	public int inc(int val) {
		value += val;
		return value;
	}

	public int dec(int val) {
		return inc(-val);
	}
}
