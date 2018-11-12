@InjectConfig(simpleBindings = {
})
package com.vadelectronics.c_core;

//Radial Expansion via Pact/Link Intermediates for Clean Architecture
/**
 * FRAMEWORK LAYER:
 *
 * UI - User interface (complex info presentation, needs interactor)
 * DB - Database (complex info access, needs interactor)
 * API - Remote api calls (complex info access, needs interactor)
 *
 * Storage - various storage media (memory, file, shared prefs etc...) *
 * Platform - various device/platform features (clock, gps, google play services)
 * 3'rd party - 3'rd party libs provided functionality (FCM, Analytics)
 *
 * DEFINITIONS:
 * UI - implements pact defined in interactor, interactor translates
 * DB - implements pact defined in interactor, interactor translates
 **/

import com.vadelectronics.x_util.inject.InjectConfig;