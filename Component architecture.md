# Component architecture

That's literally the file name ;\

## Summary

Component architecture is a software architecture that is based on the idea of components. Components are self-contained, reusable, and replaceable parts of a system that encapsulate implementation and expose a set of interfaces. Components communicate with each other via interfaces.

We use the component architecture defined by Van Der Aalst et al. (2002) as a basis for our architecture.
This paper defines a component as having a name, specification and architecture.
A component can contain other components that are specified by placeholders.

We implement this architecture using packages, interfaces and classes.
The classes are the implementation of the components, or, in other words, the architecture.
The packages ensure that components that have nothing to do with each other cannot see each other's implementation.
The interfaces are the specifications of the components.
These interfaces enable communication between components.
Components in the same package can see each other and are thus not entirely separate, but this is necessary for the working of these components.

## Sources

- Van Der Aalst, W. M. P., Van Hee, K., & Van Der Toorn, R. A. (2002). Component-based software architectures: a framework based on inheritance of behavior. Science of Computer Programming, 42(2–3), 129–171. <https://doi.org/10.1016/s0167-6423(01)00005-3>
- <https://medium.com/omarelgabrys-blog/component-based-architecture-3c3c23c7e348>
- <https://en.wikipedia.org/wiki/Component-based_software_engineering>
