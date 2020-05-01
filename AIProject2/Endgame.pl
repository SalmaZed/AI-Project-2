:- include('KB2').

ironman(X,Y,S,L2):-
	(
		A = collect,
		S = result(A,S1),
		comparestones(L2,(X,Y)),
		remove((X,Y),L2,L1),
		ironman(X,Y,S1,L1)
	);
	(
		A = up,
		T is X+1,
		size(B,_),
		T < B,
		X >= 0,
		S = result(A,S1),
		ironman(T,Y,S1,L2)
	);
	(
		A = down,
		T is X-1,
		size(B,_),
		T >= 0,
		X < B, 
		S = result(A,S1),
		ironman(T,Y,S1,L2)
	);
	(
		A = right,
		T is Y-1,
		T >= 0,
		size(_,B),
		Y < B,
		S = result(A,S1),
		ironman(X,T,S1,L2)
	);
	(
		A = left,
		T is Y+1,
		size(_,B),
		T < B,
		Y >= 0,
		S = result(A,S1),
		ironman(X,T,S1,L2)
	).

comparestones([(H1,H2)], (H1, H2)).
comparestones([(H1,H2),_|_], (H1, H2)).
comparestones([(_,_),H3|T], (X, Y)) :- comparestones([H3|T], (X, Y)).

remove((X,Y),[],[]).
remove((X,Y),[(X,Y)|T],L2):- remove((X,Y),T,L2).
remove((X,Y),[(A,B)|T],[(A,B)|S]):- 
	(X,Y)\=(A,B),
	remove((X,Y),T,S). 


snapped(S):-
	thanos(X,Y),
	S = result(snap,S1),
	stones(L1),
	ironman(X,Y,S1,L1).