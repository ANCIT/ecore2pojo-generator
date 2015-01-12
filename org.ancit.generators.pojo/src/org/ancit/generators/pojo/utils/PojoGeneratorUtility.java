package org.ancit.generators.pojo.utils;

import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.impl.EEnumImpl;
import org.eclipse.emf.ecore.impl.EReferenceImpl;

public class PojoGeneratorUtility {

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static String getExtentnsName(EList<EClass> eSuperTypes) {
		String superTypes = new String();
		for (EClass sClass : eSuperTypes) {

			if (!sClass.isInterface()) {
				if (eSuperTypes.indexOf(sClass) + 1 == eSuperTypes.size()) {
					superTypes += sClass.getName();
				} else {
					superTypes += sClass.getName() + ",";
				}
			}

		}
		if (superTypes.endsWith(",")) {
			superTypes = superTypes.substring(0, superTypes.length() - 1);
		}
		return superTypes;

	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static ArrayList<String> getImplementsMethodName(
			EStructuralFeature eStructuralFeature) {
		ArrayList<String> fieldNames = new ArrayList<String>();

		fieldNames.add(eStructuralFeature.getName());
		if (null != eStructuralFeature.getEType().getInstanceClassName()) {
			fieldNames
					.add(eStructuralFeature.getEType().getInstanceClassName());
		} else {
			fieldNames.add(eStructuralFeature.getEType().getName());
		}
		String fieldName = eStructuralFeature.getName();
		String fieldName_Method = fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1, fieldName.length());
		fieldNames.add(fieldName_Method);
		String fieldType = eStructuralFeature.getEType().getInstanceClassName();
		fieldNames.add(fieldType);

		return fieldNames;

	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static String getImplementsName(EList<EClass> eSuperTypes) {

		String implTypes = new String();
		for (EClass sClass : eSuperTypes) {

			if (sClass.isInterface()) {

				if (eSuperTypes.indexOf(sClass) + 1 == eSuperTypes.size()) {
					implTypes += sClass.getName();
				} else {
					implTypes += sClass.getName() + ",";
				}

			}
		}
		if (implTypes.endsWith(",")) {
			implTypes = implTypes.substring(0, implTypes.length() - 1);
		}

		return implTypes;

	}

	/**
	 * 
	 * @param eSuperTypes
	 * @return
	 */
	public static String getEEnumLiteral(EList<EEnumLiteral> eSuperTypes) {
		String implTypes = new String();
		for (EEnumLiteral sClass : eSuperTypes) {
			implTypes += sClass.getName() + " (" + sClass.getValue() + ") ,";

		}
		if (implTypes.endsWith(",")) {
			implTypes = implTypes.substring(0, implTypes.length() - 1);
			implTypes += ";";
		}

		return implTypes;

	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static ArrayList<String> getVariableName(
			EStructuralFeature eStructuralFeature) {
		ArrayList<String> fieldNames = new ArrayList<String>();
		if (eStructuralFeature.getEType() instanceof EEnumImpl) {
			return null;
		}
		if (eStructuralFeature instanceof EReferenceImpl) {

			if (eStructuralFeature.getUpperBound() < 0
					|| eStructuralFeature.getUpperBound() > 1) {
				fieldNames.add(eStructuralFeature.getName());
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append("java.util.List<");
				stringBuffer.append(eStructuralFeature.getEGenericType()
						.getERawType().getName());

				stringBuffer.append(">");
				fieldNames.add(stringBuffer.toString());

				String fieldName = eStructuralFeature.getName();
				String fieldName_Method = fieldName.substring(0, 1)
						.toUpperCase()
						+ fieldName.substring(1, fieldName.length());
				fieldNames.add(fieldName_Method);

				fieldNames.add(stringBuffer.toString());

			} else {
				fieldNames.add(eStructuralFeature.getName());
				fieldNames.add(eStructuralFeature.getEType().getName());
				String fieldName = eStructuralFeature.getName();
				String fieldName_Method = fieldName.substring(0, 1)
						.toUpperCase()
						+ fieldName.substring(1, fieldName.length());
				fieldNames.add(fieldName_Method);
				String fieldType = eStructuralFeature.getEType().getName();
				fieldNames.add(fieldType);

			}
		} else if (eStructuralFeature instanceof EAttributeImpl) {
			fieldNames.add(eStructuralFeature.getName());
			if (null != eStructuralFeature.getEType().getInstanceClassName()) {
				fieldNames.add(eStructuralFeature.getEType()
						.getInstanceClassName());
			} else {
				fieldNames.add(eStructuralFeature.getEType().getName());
			}
			String fieldName = eStructuralFeature.getName();
			String fieldName_Method = fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1, fieldName.length());
			fieldNames.add(fieldName_Method);
			String fieldType = eStructuralFeature.getEType()
					.getInstanceClassName();
			fieldNames.add(fieldType);

		}
		return fieldNames;

	}
}
