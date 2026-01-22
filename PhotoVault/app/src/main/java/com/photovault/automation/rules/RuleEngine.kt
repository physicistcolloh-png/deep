package com.photovault.automation.rules

class RuleEngine {
    private val rules = mutableListOf<AutomationRule>()

    fun addRule(rule: AutomationRule) {
        rules.add(rule)
    }

    fun removeRule(ruleId: String): Boolean {
        return rules.removeAll { it.id == ruleId }
    }

    fun enableRule(ruleId: String): Boolean {
        val rule = rules.find { it.id == ruleId } ?: return false
        val index = rules.indexOf(rule)
        rules[index] = rule.copy(isEnabled = true)
        return true
    }

    fun disableRule(ruleId: String): Boolean {
        val rule = rules.find { it.id == ruleId } ?: return false
        val index = rules.indexOf(rule)
        rules[index] = rule.copy(isEnabled = false)
        return true
    }

    fun getRules(): List<AutomationRule> = rules.toList()

    fun evaluateRules(context: Map<String, Any>): List<AutomationRule> {
        return rules.filter { rule ->
            rule.isEnabled && rule.conditions.all { (key, value) ->
                context[key] == value
            }
        }
    }
}
