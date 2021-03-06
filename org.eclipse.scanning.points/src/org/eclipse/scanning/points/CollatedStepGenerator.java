/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.scanning.points;

import org.eclipse.scanning.api.ModelValidationException;
import org.eclipse.scanning.api.points.ScanPointIterator;
import org.eclipse.scanning.api.points.models.CollatedStepModel;
import org.eclipse.scanning.api.points.models.StepModel;

/**
 * An iterator along points where one or more axes move to the same value together.
 * TODO: this class should be reimplemented in jython or removed.
 */
public class CollatedStepGenerator extends StepGenerator {

	CollatedStepGenerator() {
		super();
		setVisible(false);
	}

	@Override
	public CollatedStepModel getModel() {
		return (CollatedStepModel) super.getModel();
	}

	@Override
	public ScanPointIterator iteratorFromValidModel() {
		final ScanPointIterator pyIterator = createPyIterator();
		return new CollatedStepIterator(this.getModel(), pyIterator);

	}

	@Override
	protected void validateModel() {
		super.validateModel();
		if (!(model instanceof CollatedStepModel)) {
			throw new ModelValidationException("The model must be a " + CollatedStepModel.class.getSimpleName(),
					model, "offset"); // TODO Not really an offset problem.
		}
	}

	@Override
	public void setModel(StepModel model) {
		if (!(model instanceof CollatedStepModel)) {
			throw new IllegalArgumentException("The model must be a " + CollatedStepModel.class.getSimpleName());
		}
		super.setModel(model);
	}

}
