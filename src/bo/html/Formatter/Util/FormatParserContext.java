package bo.html.Formatter.Util;

import java.util.Stack;
import java.util.Vector;

import util.commonUTIL;

public class FormatParserContext extends Object {
    public static final int INCLUDE = 1;
    public static final int INLINE = 2;
    public static final int SET = 3;
    public static final int ITERATE = 4;

    public Stack _stack = null;
    public boolean _inConditional = false;
    private boolean _conditionMet = false;
    private boolean _isValid = false;
    private boolean _validBlock = true;
    private boolean _iterating = false;
    private String _iterator = null;
    private Vector _queue = null;
    public FormatParserContext() {
        _stack = new Stack();
        setDefaults(true);
    }
    public void setDefaults() {
        setDefaults(isAllValid());
    }

    public boolean isAllValid() {
        return isValidBlock()
                & (!inConditional() || isValid());
    }

    public void setDefaults(boolean validBlock) {
        _validBlock = validBlock;
        reset();
    }

    public void reset() {
        _inConditional = false;
        _conditionMet = false;
        _isValid = false;
    }

    public boolean isValidBlock() {
        return _validBlock;
    }

    public boolean isConditionMet() {
        return _conditionMet;
    }

    public boolean isValid() {
        return _isValid;
    }

    public boolean inConditional() {
        return _inConditional;
    }

    public boolean isIterating() {
        return _iterating;
    }

    public Boolean getValidBlock() {
        return Boolean.valueOf(_validBlock);
    }

    public Boolean getConditionMet() {
        return Boolean.valueOf(_conditionMet);
    }

    public Boolean getValid() {
        return Boolean.valueOf(_isValid);
    }

    public Boolean getConditional() {
        return Boolean.valueOf(_inConditional);
    }

    public Boolean getIterating() {
        return Boolean.valueOf(_iterating);
    }

    public void setValidBlock(boolean b) {
        _validBlock = b;
    }

    public void setConditionMet(boolean b) {
        _conditionMet = b;
    }

    public void setValid(boolean b) {
        _isValid = b;
    }

    public void setConditional(boolean b) {
        _inConditional = b;
    }

    public void setIterating(boolean b) {
        _iterating = b;
    }

    public void pushStack() {
        _stack.push(getConditional());
        _stack.push(getValid());
        _stack.push(getConditionMet());
        _stack.push(getValidBlock());
        if (isIterating()) {
            _stack.push(_iterator);
            _stack.push(_queue);
        }
        _stack.push(getIterating());
        setDefaults();
    }

    public void popStack() {
        Boolean b = (Boolean) _stack.pop();
        setIterating(b.booleanValue());
        if (isIterating()) {
            _queue = (Vector) _stack.pop();
            _iterator = (String) _stack.pop();
        }
        b = (Boolean) _stack.pop();
        setValidBlock(b.booleanValue());
        b = (Boolean) _stack.pop();
        setConditionMet(b.booleanValue());
        b = (Boolean) _stack.pop();
        setValid(b.booleanValue());
        b = (Boolean) _stack.pop();
        setConditional(b.booleanValue());
    }

    public void execute(int instruction, String argument) {
        if (argument == null)
            execute(instruction, (Vector) null);
        else {
            Vector arguments = new Vector();
            arguments.addElement(argument);
            execute(instruction, arguments);
        }
    }
    private void startIteration(String iterator) {
        pushStack();
        _iterating = true;
        _iterator = iterator;
        _queue = new Vector();
    }

    private void endIteration() {
        if (!_iterating
                || _iterator == null)
            return;

        bo.swift.Formatter.FormatterIterator iter = FormatBuilder.getIterator(_iterator);
        commonUTIL.display("FormatterParser", "Iterator = "
                + iter);
        if (iter == null) {
        	commonUTIL.display("FormatterParser", "Cannot locate iterator: "
                    + _iterator);
        } else {
        	commonUTIL.display("FormatterParser", "Iterator size = "
                    + iter.size());
            while (iter.hasNext()) {
                // We don't really care about the objects themselves,
                // but we need to traverse through the iterator so
                // we fetch it anyway
                Object o = iter.next();
                if (_queue != null) {
                    for (int i = 0; i < _queue.size(); i++) {
                        Command command = (Command) _queue.elementAt(i);
                        execute(command);
                    }
                }
                FormatBuilder.doKeywordSubstitution();
            }
        }
        _iterating = false;
        _iterator = null;
        _queue = null;
        popStack();
    }

    public void execute(int instruction, Vector arguments) {
        if (!isAllValid())
            return;

        if (instruction == ITERATE) {
            if (arguments == null) {
                // End of iteration
                endIteration();
            } else {
                startIteration((String) arguments.elementAt(0));
            }
            return;
        }

        Command command = new Command(instruction, arguments);
        if (_iterator != null) {
            _queue.addElement(command);
        } else {
            execute(command);
        }
    }

    private void execute(Command command) {
        int instruction = command.getInstruction();
        Vector arguments = command.getArguments();
        if (instruction == INCLUDE) {
            pushStack();
            FormatBuilder.parseInclude((String) arguments.elementAt(0));
            popStack();
        } else if (instruction == INLINE) {
            String str = (String) arguments.elementAt(0);
            StringBuffer b = new StringBuffer();
            int index = str.indexOf("\\\"");
            while (index != -1) {
                b.append(str.substring(0, index));
                b.append("\"");
                str = str.substring(index + 2);
                index = str.indexOf("\\\"");
            }
            b.append(str);
            FormatBuilder.addLine(b.toString());
        } else if (instruction == SET) {
            Object identifier = arguments.elementAt(0);
            Object value = arguments.elementAt(1);
            FormatBuilder.setKeywordValue(identifier, value);
        }
    }
    class Command extends Object {
        private int _instruction;
        private Vector _arguments;

        public Command(int instruction, Vector arguments) {
            _instruction = instruction;
            _arguments = arguments;
        }

        public int getInstruction() {
            return _instruction;
        }

        public Vector getArguments() {
            return _arguments;
        }
    }
}
