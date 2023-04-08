JFLAGS	:= -g -d $(OBJ_DIR)
JC		:= javac
BUILD	:= .
OBJ_DIR	:= $(BUILD)/obj
APP_DIR	:= $(BUILD)
TARGET	:= $(BUILD)/Stocks
.SUFFIXES: .java .class
.java.class:
        $(JC) $(JFLAGS) $*.java

CLASSES = $(wildcard src/*.java) $(wildcard src/tasks*.java)

default: classes

classes: $(CLASSES:.java=.class)

clean:
        $(RM) *.class