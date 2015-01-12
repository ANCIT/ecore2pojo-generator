package org.ancit.generators.pojo.templates;

import org.eclipse.emf.ecore.*;
import java.util.*;
import org.eclipse.emf.common.util.*;
import org.ancit.generators.pojo.utils.*;
import org.eclipse.emf.ecore.impl.*;

public class PojoClassGeneratorTemplate
{
  protected static String nl;
  public static synchronized PojoClassGeneratorTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    PojoClassGeneratorTemplate result = new PojoClassGeneratorTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "public class ";
  protected final String TEXT_2 = " ";
  protected final String TEXT_3 = " extends ";
  protected final String TEXT_4 = "  ";
  protected final String TEXT_5 = " ";
  protected final String TEXT_6 = " implements ";
  protected final String TEXT_7 = "  ";
  protected final String TEXT_8 = "  {";
  protected final String TEXT_9 = NL + "\t";
  protected final String TEXT_10 = NL + "\t";
  protected final String TEXT_11 = "private";
  protected final String TEXT_12 = " ";
  protected final String TEXT_13 = " ";
  protected final String TEXT_14 = ";" + NL + "\t ";
  protected final String TEXT_15 = NL + "\t ";
  protected final String TEXT_16 = NL + "\t";
  protected final String TEXT_17 = NL + "\t/**" + NL + "\t * The constructor" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_18 = "() {" + NL + "\t// TODO Auto-generated constructor stub" + NL + "\t}" + NL + "\t";
  protected final String TEXT_19 = NL + "\t";
  protected final String TEXT_20 = NL + "\t";
  protected final String TEXT_21 = NL + "\t/**" + NL + "\t * @return the ";
  protected final String TEXT_22 = NL + "\t */\t\t\t            " + NL + "\tpublic ";
  protected final String TEXT_23 = " get";
  protected final String TEXT_24 = "(){" + NL + "" + NL + "        return this.";
  protected final String TEXT_25 = ";" + NL + "" + NL + "    }" + NL + "    /**" + NL + "\t * @param ";
  protected final String TEXT_26 = " the ";
  protected final String TEXT_27 = " to set" + NL + "\t */" + NL + "\tpublic void set";
  protected final String TEXT_28 = "(";
  protected final String TEXT_29 = " ";
  protected final String TEXT_30 = "){" + NL + "        this.";
  protected final String TEXT_31 = " = ";
  protected final String TEXT_32 = ";" + NL + "    }";
  protected final String TEXT_33 = NL + "  @Override" + NL + " public ";
  protected final String TEXT_34 = " get";
  protected final String TEXT_35 = "(){" + NL;
  protected final String TEXT_36 = NL + "        return 0;";
  protected final String TEXT_37 = " " + NL + "         return null;";
  protected final String TEXT_38 = NL + "  }" + NL + " ";
  protected final String TEXT_39 = NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    EClass eClass = (EClass)argument;
    EList<EClass> eSuperTypes = eClass.getESuperTypes();
String extendsName = PojoGeneratorUtility.getExtentnsName(eSuperTypes);
String implementsName = PojoGeneratorUtility.getImplementsName(eSuperTypes);
    stringBuffer.append(TEXT_1);
    stringBuffer.append(eClass.getName());
    stringBuffer.append(TEXT_2);
    if(extendsName.length()>0){
    stringBuffer.append(TEXT_3);
    stringBuffer.append(extendsName);
    stringBuffer.append(TEXT_4);
    }
    stringBuffer.append(TEXT_5);
    if(implementsName.length()>0){
    stringBuffer.append(TEXT_6);
    stringBuffer.append(implementsName);
    stringBuffer.append(TEXT_7);
    }
    stringBuffer.append(TEXT_8);
    EList<EStructuralFeature> eStructuralFeatures = eClass.getEStructuralFeatures();
	for (EStructuralFeature eStructuralFeature : eStructuralFeatures) {
	ArrayList classDesc = PojoGeneratorUtility.getVariableName(eStructuralFeature);
    stringBuffer.append(TEXT_9);
    if(null!=classDesc){
    stringBuffer.append(TEXT_10);
    if(!eClass.isInterface()){
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_12);
    stringBuffer.append(classDesc.get(1));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(classDesc.get(0));
    stringBuffer.append(TEXT_14);
    }
    stringBuffer.append(TEXT_15);
    }
    stringBuffer.append(TEXT_16);
    if(!eClass.isInterface()){
    stringBuffer.append(TEXT_17);
    stringBuffer.append(eClass.getName());
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    for (EStructuralFeature eStructuralFeature : eStructuralFeatures) {
	ArrayList classDesc = PojoGeneratorUtility.getVariableName(eStructuralFeature);
    stringBuffer.append(TEXT_20);
    if(null!=classDesc){
    stringBuffer.append(TEXT_21);
    stringBuffer.append(classDesc.get(0));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(classDesc.get(3));
    stringBuffer.append(TEXT_23);
    stringBuffer.append(classDesc.get(2));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(classDesc.get(0));
    stringBuffer.append(TEXT_25);
    stringBuffer.append(classDesc.get(0));
    stringBuffer.append(TEXT_26);
    stringBuffer.append(classDesc.get(0));
    stringBuffer.append(TEXT_27);
    stringBuffer.append(classDesc.get(2));
    stringBuffer.append(TEXT_28);
    stringBuffer.append(classDesc.get(3));
    stringBuffer.append(TEXT_29);
    stringBuffer.append(classDesc.get(0));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(classDesc.get(0));
    stringBuffer.append(TEXT_31);
    stringBuffer.append(classDesc.get(0));
    stringBuffer.append(TEXT_32);
    }
    }
    if(implementsName.length()>0){
 	for (EClass sClass : eSuperTypes) {
			if (sClass.isInterface()) {
				 eStructuralFeatures = sClass
						.getEStructuralFeatures();
				for (EStructuralFeature eStructuralFeature : eStructuralFeatures) {
 ArrayList implMethodName =  PojoGeneratorUtility.getVariableName(eStructuralFeature);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(implMethodName.get(1));
    stringBuffer.append(TEXT_34);
    stringBuffer.append(implMethodName.get(2));
    stringBuffer.append(TEXT_35);
    if(((String) implMethodName.get(1)).equalsIgnoreCase("int")){
    stringBuffer.append(TEXT_36);
    } else{
    stringBuffer.append(TEXT_37);
    }
    stringBuffer.append(TEXT_38);
    }
    }
    }
    }
    stringBuffer.append(TEXT_39);
    return stringBuffer.toString();
  }
}
