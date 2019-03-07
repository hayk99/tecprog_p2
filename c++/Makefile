.SUFFIXES: .cc .o

PROG=main
SRCS=main.cc
OBJS=$(SRCS:.cc=.o)

all: $(PROG)

main: main.o 
	g++ -std=c++11 -Wall -o main $^

%.o: %.cc
	g++ -std=c++11 -Wall -c $<

.PHONY: edit go

edit:
	@geany -s -i $(SRCS) &

go:
	@$(PROG)

clean:
	@rm -f $(PROG) *.o core tags

main.o: agrupacion-estatica.h agrupacion-dinamica.h
