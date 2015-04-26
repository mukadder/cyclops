package com.aol.cyclops.matcher;


import static com.aol.cyclops.matcher.Predicates.ANY;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import com.aol.cyclops.matcher.builders.Matching;

public class ScalaParserExample {

	//http://kerflyn.wordpress.com/2011/02/14/playing-with-scalas-pattern-matching/
	//See C# impl here :- https://github.com/dotnet/roslyn/issues/206
	public Integer eval(Expression expression, int xValue){
		
		return Matching.newCase().isType( (X x)-> xValue)
				.newCase().isType((Const c) -> c.getValue())
				.newCase().isType((Add a) ->  eval(a.getLeft(),xValue) + eval(a.getRight(),xValue))
				.newCase().isType( (Mult m) -> eval(m.getLeft(),xValue) * eval(m.getRight(),xValue))
				.newCase().isType( (Neg n) ->  -eval(n.getExpr(),xValue))
				.match(expression).orElse(1);
		
		
		
	}
	
	public Expression derive(Expression e){
		return Matching.newCase().isType((X x)-> new Const(1))
					.newCase().isType((Const c)-> new Const(0))
					.newCase().isType((Add a) -> new Add(derive(a.getLeft()),derive(a.getRight())))
					.newCase().isType( (Mult m) -> new Add(new Mult(derive(m.getLeft()), m.getRight()), new Mult(m.getLeft(), derive(m.getRight()))))
					.newCase().isType( (Neg n) -> new Neg(derive(n.getExpr())))
					.match(e).get();
					
	}
	
	
	Expression simplify(Expression e)
	{
		

		
		return Matching.<Expression>adtCase().isType( (Mult m)->new Const(0)).with(new Const(0),ANY())
						.adtCase().isType( (Mult m)->new Const(0)).with(ANY(),new Const(0))
						.adtCase().isType((Mult m)-> simplify(m.right)).with(new Const(1))
						.adtCase().isType( (Mult m) -> simplify(m.getLeft())).with(ANY(),new Const(1))
						.adtCase().isType( (Mult<Const,Const> m) -> new Const(m.left.value * m.right.value))
													.with(ANY(Const.class),ANY(Const.class))
						.adtCase().isType((Add a) -> simplify(a.right)).with(new Const(0),ANY())
						.adtCase().isType((Add a)-> simplify(a.left)).with(ANY(),new Const(0))
						.adtCase().isType( (Add<Const,Const> a) -> new Const(a.left.value + a.right.value)).with(ANY(Const.class), ANY(Const.class))
						.adtCase().isType( (Neg<Const> n) -> new Const(-n.expr.value)).with(new Neg<Const>(null),ANY(Const.class))
												
						
				.unapply(e).orElse(e);

	}
	
	//Sealed case classes
	
	@AllArgsConstructor(access=AccessLevel.PRIVATE) static abstract class  Expression implements Decomposable{}
	final static class X extends Expression{ }
	@Value final static class Const extends Expression  implements Decomposable { int value; }
	@Value final static class Add<T extends Expression, R extends Expression> extends Expression implements Decomposable { T left; R right; }
	@Value final static class Mult<T extends Expression, R extends Expression> extends Expression  implements Decomposable { T left; R right; }
	@Value final static class Neg<T extends Expression> extends Expression  implements Decomposable { T expr; }
	
	
}
