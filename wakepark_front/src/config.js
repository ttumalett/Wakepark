// If this service was abstracted to a plugin, we would instead pass
// this information to the plugin's config function. For now, we
// export this object for simplicity. In a larger application, there
// would be other, unrelated settings in the config as well.
export default {
  sessionUser: null,
  sessionUserStatus: null
};
