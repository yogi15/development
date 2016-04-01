package bo.swift.Formatter;



public class BaseFormattorContext {
	

  // The following store the context of the formatter
  // They are used in the case of nested iterator
  private FormatterIterator _iterator;
  private Object _iteratorObject;
  private Object _previousIteratorObject;
  private int _iteration;

  public BaseFormattorContext() {
    _iterator = null;
    _iteratorObject = null;
    _iteration = 0;
  }

  public int getIteration() {return _iteration;}
  public FormatterIterator getIterator() { return _iterator;}
  public Object getIteratorObject() { return _iteratorObject;}
  public Object getPreviousIteratorObject() { return _previousIteratorObject;}

  public void setIteration(int iteration) { _iteration = iteration;}
  public void setIterator(FormatterIterator iterator ) {_iterator = iterator;}
  public void setIteratorObject(Object iteratorObject) {
    _iteratorObject = iteratorObject;
  }
  public void setPrevioudIteratorObject(Object previousIteratorObject) {
    _previousIteratorObject = previousIteratorObject;
  }





}
