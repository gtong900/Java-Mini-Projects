package edu.nyu.cs9053.homework7;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class Wallet<T> implements ArrayCreator<T>{
	private final AtomicReference<T[]> store;
	private int size;
	public static final int DEFAULT_SIZE = 10;
	private ArrayCreator<T> arrayCreator;

	public Wallet(ArrayCreator<T> arrayCreator) { this(arrayCreator, DEFAULT_SIZE); }

	public Wallet(ArrayCreator<T> arrayCreator,int initNumber) {
		this.arrayCreator = arrayCreator;
		this.store = new AtomicReference<>();
		this.store.set(arrayCreator.createCurrency(initNumber));
		this.size = 0;
	}

	public T[] createCurrency(int quantity) {
		T[] initStore = (T[]) new Object[quantity];
		return initStore;
	}

	public boolean add(T coin) {

		if (this.contains(coin)) {
			return false;
		}
		int arrayLength = this.store.get().length;
		if ( size >= arrayLength) {
			T[] newWalletArray = arrayCreator.createCurrency(arrayLength * 2);
			java.lang.System.arraycopy(this.store.get(), 0, newWalletArray, 0, arrayLength);
			this.store.set(newWalletArray);
		}
		store.get()[size] = coin;
		size++;
		return true;

	}

	public boolean contains(T coin) {
		if (coin == null) {
			return false;
		}

		for (int i = 0 ; i < this.size ; i++) {
			if (store.get()[i].equals(coin)) {
				return true;
			}
		}
		return false;
	}

	public boolean remove(T coin) {
		if (coin == null) {
			return false;
		}

		if (this.contains(coin)) {
			int arrayLength = this.store.get().length;
			int exsitingIndex = -1;
			size--;
			T[] newStore = (T[])new Object[arrayLength];
			for (int i = 0; i < arrayLength; i++) {
				if (coin.equals(this.store.get()[i])) {
					exsitingIndex = i;
					break;
				}
			}
			System.arraycopy(this.store.get(), 0, newStore, 0, exsitingIndex);
			System.arraycopy(this.store.get(), exsitingIndex + 1, newStore, exsitingIndex, arrayLength - (exsitingIndex + 1));
			this.store.set(newStore);
			return true;
		} else {
			return false;
		}
	}

	public T get(int x) {
		int maxBound = this.size;
		int minBound = 0;
		if (x >= minBound && x < maxBound) {
			return store.get()[x];
		} else {
			return null;
		}
	}

	public int size() {
		return this.size;
	}
}


