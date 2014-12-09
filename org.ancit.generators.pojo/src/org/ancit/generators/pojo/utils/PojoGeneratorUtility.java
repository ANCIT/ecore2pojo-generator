package org.ancit.generators.pojo.utils;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.impl.EReferenceImpl;

public class PojoGeneratorUtility {
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static ArrayList<String> getVariableName(
			EStructuralFeature eStructuralFeature) {
		ArrayList<String> fieldNames = new ArrayList<String>();

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
				fieldNames.add(eStructuralFeature.getEType()
						.getInstanceClassName());
				String fieldName = eStructuralFeature.getName();
				String fieldName_Method = fieldName.substring(0, 1)
						.toUpperCase()
						+ fieldName.substring(1, fieldName.length());
				fieldNames.add(fieldName_Method);
				String fieldType = eStructuralFeature.getEType()
						.getInstanceClassName();
				fieldNames.add(fieldType);

			}
		} else if (eStructuralFeature instanceof EAttributeImpl) {
			fieldNames.add(eStructuralFeature.getName());
			fieldNames
					.add(eStructuralFeature.getEType().getInstanceClassName());
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
