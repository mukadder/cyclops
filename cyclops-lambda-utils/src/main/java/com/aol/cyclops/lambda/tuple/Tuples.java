package com.aol.cyclops.lambda.tuple;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

import com.aol.cyclops.lambda.api.TupleWrapper;



/**
 * Iteroperability class for different Tuple implementation - wrap in Tuple of appropriate arity
 * 
 * @author johnmcclean
 *
 * @param <T1>
 * @param <T2>
 * @param <T3>
 * @param <T4>
 * @param <T5>
 * @param <T6>
 */
public class Tuples<T1,T2,T3,T4,T5,T6,T7,T8> implements TupleWrapper,  Tuple8<T1,T2,T3,T4,T5,T6,T7,T8>
															{
	@Getter
	private final Object instance;
	@Getter
	private final List<Object> cachedValues;
	
	public Tuples(Object tuple){
		this.instance = tuple;
		if(tuple instanceof List)
			cachedValues = ((List)tuple);
		else
			cachedValues = values();
	}
	
	public static <T1> Tuple1<T1> of(T1 t1){
		return (Tuple1)new Tuples(Arrays.asList(t1));
	}
	
	public static <T1,T2> Tuple2<T1,T2> of(T1 t1, T2 t2){
		return (Tuple2)new Tuples(Arrays.asList(t1,t2));
	}
	
	public static <T1,T2,T3> Tuple3<T1,T2,T3> of(T1 t1, T2 t2,T3 t3){
		return (Tuple3)new Tuples(Arrays.asList(t1,t2,t3));
	}
	
	public static <T1,T2,T3,T4> Tuple4<T1,T2,T3,T4> of(T1 t1, T2 t2,T3 t3,T4 t4){
		return (Tuple4)new Tuples(Arrays.asList(t1,t2,t3,t4));
	}
	
	public static <T1,T2,T3,T4,T5> Tuple5<T1,T2,T3,T4,T5> of(T1 t1, T2 t2,T3 t3,T4 t4,T5 t5){
		return (Tuple5)new Tuples(Arrays.asList(t1,t2,t3,t4,t5));
	}
	
	public static <T1,T2,T3,T4,T5,T6> Tuple6<T1,T2,T3,T4,T5,T6> of(T1 t1, T2 t2,T3 t3,T4 t4,T5 t5, T6 t6){
		return (Tuple6)new Tuples(Arrays.asList(t1,t2,t3,t4,t5,t6));
	}
	public static <T1,T2,T3,T4,T5,T6,T7> Tuple7<T1,T2,T3,T4,T5,T6,T7> of(T1 t1, T2 t2,T3 t3,T4 t4,T5 t5,
			T6 t6, T7 t7){
		return (Tuple7)new Tuples(Arrays.asList(t1,t2,t3,t4,t5,t6,t7));
	}
	
	public static <T1,T2,T3,T4,T5,T6,T7,T8> Tuple8<T1,T2,T3,T4,T5,T6,T7,T8> of(T1 t1, T2 t2,T3 t3,T4 t4,T5 t5,
			T6 t6, T7 t7,T8 t8){
		return (Tuple8)new Tuples(Arrays.asList(t1,t2,t3,t4,t5,t6,t7,t8));
	}
	
}