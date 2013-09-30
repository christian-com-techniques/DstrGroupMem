JAVAC=javac
OUTPUTDIR=bin/
FILES = src/ConnectionHandler.java\
	src/DstrGroupMembership.java\
	src/DstrMarshaller.java\
	src/MembershipController.java\
	src/MembershipEntry.java\
	src/MembershipList.java\
	src/OwnIP.java\
	src/Supplier.java\
	src/UserInputHandler.java


all : $(FILES)
	-@mkdir $(OUTPUTDIR)
	javac $(FILES) -d $(OUTPUTDIR)

clean : 
	-@rm -R bin
	-@rm -R out
	-@rm *~
