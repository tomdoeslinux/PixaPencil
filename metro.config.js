const { getDefaultConfig } = require('expo/metro-config');

const config = getDefaultConfig(__dirname);

config.resolver.assetExts.push(
  // Add support for fetching .glsl file-types
  'glsl'
);

module.exports = config;
