/* This file was generated by SableCC (http://www.sablecc.org/). */

package decaf.node;

import decaf.analysis.*;

@SuppressWarnings("nls")
public final class TComment1 extends Token
{
    public TComment1(String text)
    {
        setText(text);
    }

    public TComment1(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TComment1(getText(), getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTComment1(this);
    }
}