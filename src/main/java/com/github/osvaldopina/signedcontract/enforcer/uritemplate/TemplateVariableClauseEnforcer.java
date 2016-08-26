package com.github.osvaldopina.signedcontract.enforcer.uritemplate;

import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.impl.VarSpec;
import com.github.osvaldopina.signedcontract.enforcer.BranchClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.ClauseEnforcer;
import com.github.osvaldopina.signedcontract.enforcer.Navigator;

import java.util.List;

/**
 * Created by deinf.osvaldo on 26/08/2016.
 */
public class TemplateVariableClauseEnforcer extends BranchClauseEnforcer<UriTemplate, VarSpec> {

    private String variableName;

    public TemplateVariableClauseEnforcer(String variableName, List<? extends ClauseEnforcer<VarSpec>> subClauses) {
        super(subClauses);
        this.variableName = variableName;

      }

    @Override
    protected Navigator<UriTemplate, VarSpec> createNavigator() {

        return super.createNavigator();
    }
}
