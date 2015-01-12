package org.ancit.generators.pojo.templates;

import org.eclipse.emf.ecore.*;
import java.util.*;
import org.eclipse.emf.common.util.*;
import org.ancit.generators.pojo.utils.*;
import org.eclipse.emf.ecore.impl.*;

public class PojoEnumGeneratorTemplate
{
  protected static String nl;
  public static synchronized PojoEnumGeneratorTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    PojoEnumGeneratorTemplate result = new PojoEnumGeneratorTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "public  enum ";
  protected final String TEXT_2 = "   {";
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + NL + "/**" + NL + " * String value of enum." + NL + " */" + NL + "    private final int value;" + NL + "    /**" + NL + "     * Get the permission type string." + NL + "     * " + NL + "     * @return String- rule constant" + NL + "     */" + NL + "    public int value() {" + NL + "        return value;" + NL + "    }" + NL + "  ";
  protected final String TEXT_5 = NL + "  ";
  protected final String TEXT_6 = "(int val) {" + NL + "\tvalue =val;" + NL + "}" + NL + "\t" + NL + " }";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    EEnumImpl eClass = (EEnumImpl)argument;
    EList<EEnumLiteral> eSuperTypes = eClass.getELiterals();
String classDesc  = PojoGeneratorUtility.getEEnumLiteral(eSuperTypes);
    stringBuffer.append(TEXT_1);
    stringBuffer.append(eClass.getName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(classDesc   );
    stringBuffer.append(TEXT_4);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(eClass.getName());
    stringBuffer.append(TEXT_6);
    return stringBuffer.toString();
  }
}
