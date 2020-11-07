# Dagger Testing
This repository belongs to the [article](https://medium.com/@skywall/integration-testing-with-dagger-2-kotlin-b568d298b0b) about integration testing with Dagger.

This repository contains multiple branches, demonstrating different approaches to tested object instantiation and
dependency mocking:
- `naive-approach` - Tested object constructed manually.
- `independent-module` - Tested object constructed by Dagger. Mocking based on module extension.
- `test-component-swiched-module` - Tested object constructed by Dagger. Mocking based on test module & component.
- `test-component-custom-factory` - Tested object constructed by Dagger. Mocking based on test component and factory.