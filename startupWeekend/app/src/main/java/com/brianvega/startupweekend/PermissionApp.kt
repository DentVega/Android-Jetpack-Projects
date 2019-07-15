package com.brianvega.startupweekend

class PermissionApp {

    var permissionAceppted: Int = 0
    lateinit var permissionName: String

    constructor(permissionAceppted: Int, permissionName: String) {
        this.permissionAceppted = permissionAceppted
        this.permissionName = permissionName
    }

    override fun toString(): String {
        return "PermissionApp(permissionAceppted=$permissionAceppted, permissionName='$permissionName')"
    }

}