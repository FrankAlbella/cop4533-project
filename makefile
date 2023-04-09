JC	:= javac
JAR	:= jar
JARFLAGS:= -cvfm
BUILD	:= $(PWD)
OBJ_DIR	:= $(BUILD)/obj
APP_DIR	:= $(BUILD)
TARGET	:= $(BUILD)/Stocks
SRC_DIR	:= $(BUILD)/src
MANIFEST:= $(BUILD)/manifest.mf
SRC	:= $(wildcard $(SRC_DIR)/tasks/*.java) \
		$(wildcard $(SRC_DIR)/*.java) 
JFLAGS	:= -g -d $(OBJ_DIR)

default: build

build:
	$(JC) $(JFLAGS) $(SRC)	
	cd $(OBJ_DIR); $(JAR) $(JARFLAGS) $(TARGET) $(MANIFEST) .
	chmod +x $(TARGET)

clean:
	$(RM) $(OBJ_DIR)/*