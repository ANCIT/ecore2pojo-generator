package org.ancit.generators.pojo.templates;

import org.eclipse.emf.ecore.*;
import org.eclipse.emf.common.util.*;

public class PojoGeneratorTemplate
{
  protected static String nl;
  public static synchronized PojoGeneratorTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    PojoGeneratorTemplate result = new PojoGeneratorTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t\t\t" + NL + "public ";
  protected final String TEXT_2 = " https://github.com/ANCIT/ecore2pojo-generator.gitinterface ";
  protected final String TEXT_3 = " class ";
  protected final String TEXT_4 = " ";
  protected final String TEXT_5 = " extends ";
  protected final String TEXT_6 = " ";
  protected final String TEXT_7 = " {" + NL + "\t" + NL + "\t";
  protected final String TEXT_8 = NL + "\t";
  protected final String TEXT_9 = "private";
  protected final String TEXT_10 = " ";
  protected final String TEXT_11 = " ";
  protected final String TEXT_12 = ";" + NL + "\t";
  protected final String TEXT_13 = NL + "\t" + NL + "\t";
  protected final String TEXT_14 = NL + "\t//Constructor" + NL + "\tpublic ";
  protected final String TEXT_15 = "() {" + NL + "\t" + NL + "\t}" + NL + "\t";
  protected final String TEXT_16 = NL + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    EClass eClass = (EClass)argument;
    EList<EClass> eSuperTypes = eClass.getESuperTypes();
					String superTypes = new String();
					for (EClass sClass : eSuperTypes) {
						if (eSuperTypes.indexOf(sClass) + 1 == eSuperTypes
								.size()) {
							superTypes += sClass.getName();
						} else {
							superTypes += sClass.getName() + ",";
						}
					}
    stringBuffer.append(TEXT_1);
    if(eClass.isInterface()){
    stringBuffer.append(TEXT_2);
    } else {
    stringBuffer.append(TEXT_3);
    }
    stringBuffer.append(eClass.getName());
    stringBuffer.append(TEXT_4);
    if(superTypes.length() > 0) {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(superTypes);
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_7);
    EList<EAttribute> eAttributes = eClass.getEAttributes();
					for (EAttribute eAttribute : eAttributes) {
    stringBuffer.append(TEXT_8);
    if(!eClass.isInterface()){
    stringBuffer.append(TEXT_9);
    }
    stringBuffer.append(TEXT_10);
    stringBuffer.append(eAttribute.getEType().getInstanceClassName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(eAttribute.getName());
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    if(!eClass.isInterface()){
    stringBuffer.append(TEXT_14);
    stringBuffer.append(eClass.getName());
    stringBuffer.append(TEXT_15);
    }
    stringBuffer.append(TEXT_16);
    return stringBuffer.toString();
  }
}
