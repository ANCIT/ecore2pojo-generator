package org.ancit.generators.pojo.templates;

import org.eclipse.emf.ecore.*;
import java.util.*;
import org.eclipse.emf.common.util.*;
import org.ancit.generators.pojo.utils.*;
import org.eclipse.emf.ecore.impl.*;

public class PojoInterfaceTemplate
{
  protected static String nl;
  public static synchronized PojoInterfaceTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    PojoInterfaceTemplate result = new PojoInterfaceTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "public  interface ";
  protected final String TEXT_2 = " ";
  protected final String TEXT_3 = " extends ";
  protected final String TEXT_4 = "  ";
  protected final String TEXT_5 = "  {";
  protected final String TEXT_6 = NL + "\tpublic ";
  protected final String TEXT_7 = " get";
  protected final String TEXT_8 = "();";
  protected final String TEXT_9 = NL + " }";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    EClass eClass = (EClass)argument;
    EList<EClass> eSuperTypes = eClass.getESuperTypes();
String extendsName = PojoGeneratorUtility.getExtentnsName(eSuperTypes);
    stringBuffer.append(TEXT_1);
    stringBuffer.append(eClass.getName());
    stringBuffer.append(TEXT_2);
    if(extendsName.length()>0){
    stringBuffer.append(TEXT_3);
    stringBuffer.append(extendsName);
    stringBuffer.append(TEXT_4);
    }
    stringBuffer.append(TEXT_5);
    EList<EStructuralFeature> eStructuralFeatures = eClass.getEStructuralFeatures();
	for (EStructuralFeature eStructuralFeature : eStructuralFeatures) {
	ArrayList classDesc = PojoGeneratorUtility.getVariableName(eStructuralFeature);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(classDesc.get(3));
    stringBuffer.append(TEXT_7);
    stringBuffer.append(classDesc.get(2));
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    return stringBuffer.toString();
  }
}
