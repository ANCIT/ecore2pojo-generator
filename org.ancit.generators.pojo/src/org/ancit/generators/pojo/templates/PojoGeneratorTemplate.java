package org.ancit.generators.pojo.templates;

import org.eclipse.emf.ecore.*;
import java.util.*;
import org.eclipse.emf.common.util.*;
import org.ancit.generators.pojo.utils.*;
import org.eclipse.emf.ecore.impl.*;

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
  protected final String TEXT_7 = " {" + NL;
  protected final String TEXT_8 = NL + "\t\t";
  protected final String TEXT_9 = "private";
  protected final String TEXT_10 = " ";
  protected final String TEXT_11 = " ";
  protected final String TEXT_12 = ";" + NL + "\t/**" + NL + "\t * @return the ";
  protected final String TEXT_13 = NL + "\t */\t\t\t            " + NL + "\tpublic ";
  protected final String TEXT_14 = " get";
  protected final String TEXT_15 = "(){" + NL + "" + NL + "        return this.";
  protected final String TEXT_16 = ";" + NL + "" + NL + "    }" + NL + "    /**" + NL + "\t * @param ";
  protected final String TEXT_17 = " the ";
  protected final String TEXT_18 = " to set" + NL + "\t */" + NL + "\tpublic void set";
  protected final String TEXT_19 = "(";
  protected final String TEXT_20 = " ";
  protected final String TEXT_21 = "){" + NL + "        this.";
  protected final String TEXT_22 = " = ";
  protected final String TEXT_23 = ";" + NL + "    }";
  protected final String TEXT_24 = NL + "\t" + NL + "\t";
  protected final String TEXT_25 = NL + "\t/**" + NL + "\t * The constructor" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_26 = "() {" + NL + "\t// TODO Auto-generated constructor stub" + NL + "\t}" + NL + "\t";
  protected final String TEXT_27 = NL + "}";

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
    EList<EStructuralFeature> eStructuralFeatures = eClass.getEStructuralFeatures();
	for (EStructuralFeature eStructuralFeature : eStructuralFeatures) {
	ArrayList classDesc = PojoGeneratorUtility.getVariableName(eStructuralFeature);
    stringBuffer.append(TEXT_8);
    if(!eClass.isInterface()){
    stringBuffer.append(TEXT_9);
    }
    stringBuffer.append(TEXT_10);
    stringBuffer.append(classDesc.get(1));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(classDesc.get(0));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(classDesc.get(0));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(classDesc.get(3));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(classDesc.get(2));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(classDesc.get(0));
    stringBuffer.append(TEXT_16);
    stringBuffer.append(classDesc.get(0));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(classDesc.get(0));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(classDesc.get(2));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(classDesc.get(3));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(classDesc.get(0));
    stringBuffer.append(TEXT_21);
    stringBuffer.append(classDesc.get(0));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(classDesc.get(0));
    stringBuffer.append(TEXT_23);
    }
    stringBuffer.append(TEXT_24);
    if(!eClass.isInterface()){
    stringBuffer.append(TEXT_25);
    stringBuffer.append(eClass.getName());
    stringBuffer.append(TEXT_26);
    }
    stringBuffer.append(TEXT_27);
    return stringBuffer.toString();
  }
}
